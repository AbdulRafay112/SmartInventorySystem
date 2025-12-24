import React, { useEffect, useState } from "react";
import InventoryService from "../services/InventoryService";
import InventoryForm from "./InventoryForm"; 

const InventoryList = () => {
  const [foods, setFoods] = useState([]);

  
  const loadFoods = () => {
    InventoryService.getAllFoods()
      .then((response) => setFoods(response.data))
      .catch((error) => console.error("Error:", error));
  };

  useEffect(() => {
    loadFoods();
  }, []);

  const handleDelete = (id) => {
    if (window.confirm("Are you sure you want to delete this item?")) {
      InventoryService.deleteFood(id)
        .then(() => {
          loadFoods(); 
        })
        .catch((err) => alert("Failed to delete"));
    }
  };


  const handleDownloadPdf = () => {
    InventoryService.downloadPdf()
      .then((response) => {
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', 'Inventory_Report.pdf'); 
        document.body.appendChild(link);
        link.click();
      })
      .catch((err) => alert("Error downloading PDF"));
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center text-primary mb-4">Smart Inventory System</h2>
      <InventoryForm onAdd={loadFoods} />

      <div className="d-flex justify-content-between align-items-center mb-3">
        <h4 className="text-secondary">Current Stock</h4>
        <button onClick={handleDownloadPdf} className="btn btn-success">
          📄 Download PDF Report
        </button>
      </div>
      
      <table className="table table-bordered table-hover shadow-sm">
        <thead className="table-dark">
          <tr>
            <th>Item Name</th>
            <th>Category</th>
            <th>Qty</th>
            <th>Unit</th>
            <th>Price</th>
            <th>Expiry Date</th>
            <th>Actions</th> 
          </tr>
        </thead>
        <tbody>
          {foods.map((food) => (
            <tr key={food.id}>
              <td>{food.itemName}</td>
              <td>{food.category}</td>
              <td>{food.quantity}</td>
              <td>{food.unit}</td>
              <td>{food.price}</td>
              <td>{food.expiryDate}</td>
              <td>
                <button 
                  onClick={() => handleDelete(food.id)} 
                  className="btn btn-danger btn-sm">
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default InventoryList;