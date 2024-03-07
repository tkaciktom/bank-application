import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import useStore from './store';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';

const LoginPage: React.FC = () => {
    const [username, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const navigate = useNavigate();
    const setToken = useStore((state) => state.setToken);

    const handleLogin = async () => {
        try {
            const response = await axios.post(`${process.env.REACT_APP_API_URL}/users/login`, { username, password });
            if (response.status === 200) {
                setToken(response.data.token);
                navigate('/bundleActivation');
            } else {
                console.error('Login failed');
            }
        } catch (error) {
            console.error('Error during login', error);
        }
    };

    return (
        <div>
            <TextField label="Login" variant="outlined" value={username} onChange={(e) => setUsername(e.target.value)} />
            <TextField label="Password" type="password" variant="outlined" value={password} onChange={(e) => setPassword(e.target.value)} />
            <Button variant="contained" onClick={handleLogin}>Login</Button>
        </div>
    );
};

export default LoginPage;
