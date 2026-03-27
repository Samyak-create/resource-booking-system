import api from "./api";
import { jwtDecode } from "jwt-decode";

const authService = {
    register: async (userData) => {
        const response = await api.post("/auth/register", userData);
        return response.data;
    },
    login: async (credentials) => {
        const response = await api.post("/auth/login", credentials);
        if (response.data) {
            localStorage.setItem("token", response.data);
        }
        return response.data;
    },
    logout: () => {
        localStorage.removeItem("token");
    },
    getCurrentUser: () => {
        return localStorage.getItem("token");
    },
    getUserInfo: () => {
        const token = localStorage.getItem("token");
        if (token) {
            try {
                return jwtDecode(token);
            } catch {
                return null;
            }
        }
        return null;
    }
};

export default authService;
