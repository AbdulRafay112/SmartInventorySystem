import React, { useState } from "react";
import InventoryService from "../services/InventoryService";

const InventoryForm = ({ onAdd }) => {
  const [food, setFood] = useState({
    itemName: "",
    category: "",
    quantity: "",
    unit: "",
    price: "",
    expiryDate: "",
  });

  const handleChange = (e) => {
    setFood({ ...food, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    InventoryService.addFood(food)
      .then((res) => {
        alert("Item Added Successfully!");
        setFood({ itemName: "", category: "", quantity: "", unit: "", price: "", expiryDate: "" });
        if(onAdd) onAdd();
      })
      .catch((err) => {
        console.error(err);
        alert("Error adding item");
      });
  };

  return (
    <div className="card p-4 shadow mb-4 bg-light">
      <h4 className="mb-3 text-secondary">Add New Item</h4>
      <form onSubmit={handleSubmit}>
        <div className="row">
          <div className="col-md-6 mb-3">
            <input type="text" name="itemName" value={food.itemName} onChange={handleChange} className="form-control" placeholder="Item Name (e.g. Rice)" required />
          </div>
          <div className="col-md-6 mb-3">
            <input type="text" name="category" value={food.category} onChange={handleChange} className="form-control" placeholder="Category (e.g. Grain)" required />
          </div>
          <div className="col-md-3 mb-3">
            <input type="number" name="quantity" value={food.quantity} onChange={handleChange} className="form-control" placeholder="Qty" required />
          </div>
          <div className="col-md-3 mb-3">
            <input type="text" name="unit" value={food.unit} onChange={handleChange} className="form-control" placeholder="Unit (kg/pkt)" required />
          </div>
          <div className="col-md-3 mb-3">
            <input type="number" name="price" value={food.price} onChange={handleChange} className="form-control" placeholder="Price" required />
          </div>
          <div className="col-md-3 mb-3">
            <input type="date" name="expiryDate" value={food.expiryDate} onChange={handleChange} className="form-control" required />
          </div>
        </div>
        <button type="submit" className="btn btn-primary w-100">Add to Inventory</button>
      </form>
    </div>
  );
};

export default InventoryForm;