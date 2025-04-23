import logo from './logo.svg';
import './App.css';
import api from './api/AxiosConfig';
import { useEffect } from 'react';
import { useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import Home from './components/home/Home';
import Layout from './components/layout';

function App() {

  const [movies, setMovies] = useState();

  const getMovies = async () => {
    try {
      const response = await api.get('/api/v1/movies');
      console.log(response.data);
      setMovies(response.data);
    } catch (error) {
      console.error('Error fetching movies:', error);
    }
  }
  useEffect(() => {
    getMovies();
  }
    , []);
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Layout/>}>
           <Route path="/" element={<Home/>}>
           </Route>
        </Route>
      </Routes>
    </div>
  );
}

export default App;
