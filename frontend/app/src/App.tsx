import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginPage from './LoginPage';
import BundleActivationPage from './BundleActivationPage';
import StatusPage from './StatusPage';

const App: React.FC = () => {
  return (
      <BrowserRouter basename={process.env.REACT_APP_PUBLIC_URL}>
        <Routes>
          <Route path="/" element={<LoginPage />} />
          <Route path="/bundleActivation" element={<BundleActivationPage />} />
          <Route path="/status" element={<StatusPage />} />
        </Routes>
      </BrowserRouter>
  );
};

export default App;
