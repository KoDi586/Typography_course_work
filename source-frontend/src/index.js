import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';

import MainPage from "./screens/MainPage"
import OrderForm from './screens/OrderForm';
import Providers from './screens/Providers';
import ProductAdd from './screens/ProductAdd';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Router>
      <Routes>
        <Route path='/' element={<MainPage/>}/>
        <Route path='/neworder' element={<OrderForm/>}/>
        <Route path='/providers' element={<Providers/>}/>
        <Route path='/productadd' element={<ProductAdd/>}/>
      </Routes>
    </Router>
  </React.StrictMode>
);