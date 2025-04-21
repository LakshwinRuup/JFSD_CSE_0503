import React, { useEffect, useState } from 'react';
import './App.css';
import axios from 'axios';

function App() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:5000/api/products')
      .then(res => setProducts(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div className="container">
      <h1>ShopStack Products</h1>
      <div className="product-grid">
        {products.map(product => (
          <div key={product._id} className="product">
            <img src={product.image} alt={product.name} />
            <h2>{product.name}</h2>
            <p>${product.price}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;
