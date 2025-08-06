import type { DashboardSearchDto } from "../dto/dahboard/DashboardSearchDto";
import axiosClient from "./axiosClient";
import { ENDPOINTS } from "./endpoints";

const dashboardApi = {
  getAllOfferedSkills: (payload?: DashboardSearchDto) => {
    const params = new URLSearchParams();

    if (payload?.category) {
      params.append("category", payload.category);
    }

    if (payload?.dateRange) {
      params.append("dateRange", payload.dateRange);
    } else {
      params.append("dateRange", "noRange"); // default value
    }

    if (payload?.sortByExperience !== undefined) {
      params.append("sortByExperience", String(payload.sortByExperience));
    }

    return axiosClient.get(
      `${ENDPOINTS.DASHBOARD.GETALLOFFEREDSKILLS}?${params.toString()}`
    );
  },
  getAllCategories: () =>
    axiosClient.get(ENDPOINTS.DASHBOARD.GETALLCATEGORIES),
};

export default dashboardApi;
