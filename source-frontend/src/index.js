import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';

import MainPage from "./screens/MainPage"
import OrderForm from './screens/OrderForm';
import Providers from './screens/Providers';
import ProductAdd from './screens/ProductAdd';
import ProductChange from './screens/ProductChange';
import ReportMaterials from './screens/ReportMaterials';
import ReportMoney from './screens/ReortMoney';
import AllMaterials from './screens/AllMaterials';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Router>
      <Routes>
        <Route path='/' element={<MainPage/>}/>
        <Route path='/neworder' element={<OrderForm/>}/>
        <Route path='/providers' element={<Providers/>}/>
        <Route path='/productadd' element={<ProductAdd/>}/>
        <Route path='/productchange' element={<ProductChange/>}/>
        <Route path='/reportmaterials' element={<ReportMaterials/>}/>
        <Route path='/reportmoney' element={<ReportMoney/>}/>
        <Route path='/allmaterials' element={<AllMaterials/>}/>
      </Routes>
    </Router>
  </React.StrictMode>
);