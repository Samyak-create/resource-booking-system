import api from "./api";

const resourceService = {
    getAllResources: async () => {
        const response = await api.get("/resources");
        return response.data;
    },
    bookResource: async (bookingData) => {
        const response = await api.post("/bookings", bookingData);
        return response.data;
    },
    addResource: async (resourceData) => {
        const response = await api.post("/resources", resourceData);
        return response.data;
    },
    deleteResource: async (id) => {
        const response = await api.delete(`/resources/delete/${id}`);
        return response.data;
    }
};

export default resourceService;
