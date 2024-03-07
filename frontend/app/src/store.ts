import create from 'zustand';

interface StoreState {
    token: string | null;
    setToken: (token: string) => void;
}

const useStore = create<StoreState>((set) => ({
    token: null,
    setToken: (token: string) => set(() => ({ token })),
}));

export default useStore;
