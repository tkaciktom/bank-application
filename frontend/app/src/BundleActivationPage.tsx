// OrderPage.tsx
import React from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Button from '@mui/material/Button';
import useStore from './store';

const BundleActivationPage: React.FC = () => {
    const navigate = useNavigate();
    const token = useStore((state) => state.token);

    const handleOrder = async () => {
        try {
            const response = await axios.post(`${process.env.REACT_APP_API_URL}/bundles/activate`, { id: '1' }, { headers: { Authorization: `Bearer ${token}` } });
            if (response.status === 200) {
                navigate('/status', { state: { status: 'OK' } });
            } else {
                navigate('/status', { state: { status: 'ERROR' } });
            }
        } catch (error) {
            navigate('/status', { state: { status: 'ERROR' } });
        }
    };

    return (
        <div>
            <p>Balíček 1</p>
            <Button variant="contained" onClick={handleOrder}>Objednat</Button>
        </div>
    );
};

export default BundleActivationPage;
