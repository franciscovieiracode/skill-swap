import axiosClient from "./axiosClient";
import { ENDPOINTS } from "./endpoints";

const dashboardApi = {
  getAllOfferedSkills: () => axiosClient.get(ENDPOINTS.DASHBOARD.GETALLOFFEREDSKILLS),

  getAllCategories: () => axiosClient.get(ENDPOINTS.DASHBOARD.GETALLCATEGORIES),
};

export default dashboardApi;
