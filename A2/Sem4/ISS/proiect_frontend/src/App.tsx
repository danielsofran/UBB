import React from 'react';
import './App.css';
import { LoginForm } from "./components/LoginForm";
import { Layout } from "./layouts/layout";
import {BrowserRouter, Routes, Route, Navigate} from "react-router-dom";
import {HomeAngajat} from "./pages/HomeAngajat";
import {SefAngajati} from "./pages/SefAngajati";
import {SefSarcini} from "./pages/SefSarcini";
import {AddSarcina} from "./pages/AddSarcina";
import {RedirectAdmin} from "./pages/RedirectAdmin";

function App() {
  return (
      <BrowserRouter>
          <Routes>
              <Route path="/">
                  <Route index element={<LoginForm />} />
                  <Route path="home_angajat" element={<HomeAngajat />} />
                  <Route path="sef_angajati" element={<SefAngajati />} />
                  <Route path="sef_sarcini" element={<SefSarcini />} />
                  <Route path="adauga_sarcina/:id/" element={<AddSarcina />} />
                  <Route path="admin" element={<RedirectAdmin />} />
              </Route>
          </Routes>
      </BrowserRouter>
  );
}

export default App;
