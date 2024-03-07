import React from 'react';
import { useLocation } from 'react-router-dom';

interface LocationState {
    status: 'OK' | 'ERROR';
}

const StatusPage: React.FC = () => {
    const location = useLocation();
    const { status } = location.state as LocationState || { status: 'ERROR' };

    return (
        <div>
            <p>{status}</p>
        </div>
    );
};

export default StatusPage;
