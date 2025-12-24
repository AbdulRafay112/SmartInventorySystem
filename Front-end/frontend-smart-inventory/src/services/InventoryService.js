import axios from "axios";

const API_URL = "http://localhost:8080/api/food";

class InventoryService {
  
  getAllFoods() {
    return axios.get(API_URL + "/all");
  }

  addFood(food) {
    return axios.post(API_URL + "/add", food);
  }

  deleteFood(id) {
    return axios.delete(API_URL + "/delete/" + id);
  }

  downloadPdf() {
    return axios.get(API_URL + "/pdf", { responseType: 'blob' });
  }
}

export default new InventoryService();