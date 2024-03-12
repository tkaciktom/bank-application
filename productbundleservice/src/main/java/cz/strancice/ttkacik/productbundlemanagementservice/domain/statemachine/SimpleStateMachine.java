package cz.strancice.ttkacik.productbundlemanagementservice.domain.statemachine;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class SimpleStateMachine<S, E> {

    @Getter
    private S currentState;
    private final Map<S, Map<E, BiConsumer<S, E>>> transitions;

    private BiConsumer<S, S> onTransitionFinished;

    public SimpleStateMachine(S initialState) {
        this.transitions = new HashMap<>();
        this.currentState = initialState;
    }

    public void addTransition(S fromState, E event, S toState, BiConsumer<S, E> action) {
        transitions.computeIfAbsent(fromState, k -> new HashMap<>())
                .put(event, (s, e) -> {
                    action.accept(toState, e);
                    this.currentState = toState;
                    if (this.onTransitionFinished != null) {
                        this.onTransitionFinished.accept(this.currentState, toState);
                    }
                });
    }

    public SimpleStateMachine<S, E> onTransitionFinished(BiConsumer<S, S> consumer) {
        this.onTransitionFinished = consumer;
        return this;
    }

    public void fireEvent(E event) {
        Map<E, BiConsumer<S, E>> stateTransitions = transitions.get(currentState);
        if (stateTransitions != null) {
            BiConsumer<S, E> transitionAction = stateTransitions.get(event);
            if (transitionAction != null) {
                transitionAction.accept(currentState, event);
            }
        }
    }

    public boolean canAccept(E event) {
        Map<E, BiConsumer<S, E>> stateTransitions = transitions.get(currentState);
        return stateTransitions != null && stateTransitions.containsKey(event);
    }


    public static class Builder<S, E> {
        private final SimpleStateMachine<S, E> stateMachine;

        public Builder(S initialState) {
            this.stateMachine = new SimpleStateMachine<>(initialState);
        }

        public Builder<S, E> externalTransition() {
            return this;
        }

        public TransitionBuilder<S, E> from(S fromState) {
            return new TransitionBuilder<>(this, fromState);
        }

        public SimpleStateMachine<S, E> build() {
            return stateMachine;
        }
    }

    public static class TransitionBuilder<S, E> {
        private final Builder<S, E> builder;
        private final S fromState;
        private S toState;
        private E event;

        public TransitionBuilder(Builder<S, E> builder, S fromState) {
            this.builder = builder;
            this.fromState = fromState;
        }

        public TransitionBuilder<S, E> to(S toState) {
            this.toState = toState;
            return this;
        }

        public TransitionBuilder<S, E> on(E event) {
            this.event = event;
            return this;
        }

        public Builder<S, E> perform(BiConsumer<S, E> action) {
            builder.stateMachine.addTransition(fromState, event, toState, action);
            return builder;
        }
    }

}

