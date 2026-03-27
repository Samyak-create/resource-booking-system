import React, { useState, useEffect } from "react";
import resourceService from "../services/resourceService";
import authService from "../services/authService";
import { useNavigate } from "react-router-dom";
import BookingForm from "../components/BookingForm";
import "./Register.css"; // Basic styling

const Dashboard = () => {
    const [resources, setResources] = useState([]);
    const [error, setError] = useState("");
    const [selectedResource, setSelectedResource] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchResources = async () => {
            try {
                const data = await resourceService.getAllResources();
                setResources(data);
            } catch (err) {
                if (err.response?.status === 403 || err.response?.status === 401) {
                    authService.logout();
                    navigate("/login");
                }
                setError("Failed to fetch resources.");
            }
        };
        fetchResources();
    }, [navigate]);

    const handleLogout = () => {
        authService.logout();
        navigate("/login");
    };

    const onBookingSuccess = () => {
        // Refresh resources
        const fetchResources = async () => {
            const data = await resourceService.getAllResources();
            setResources(data);
        };
        fetchResources();
        setSelectedResource(null);
    };

    return (
        <div className="dashboard-container">
            <header className="dashboard-header">
                <h1>Resource Dashboard</h1>
                <div>
                    {authService.getUserInfo()?.role === 'ROLE_ADMIN' && (
                        <button onClick={() => navigate("/admin")} className="book-button" style={{marginRight: '10px'}}>Admin Dashboard</button>
                    )}
                    <button onClick={handleLogout} className="logout-button">Logout</button>
                </div>
            </header>

            {error && <p className="error-message">{error}</p>}

            <div className="resource-list">
                {resources.map((res) => (
                    <div key={res.id} className="resource-card">
                        <h3>{res.name}</h3>
                        <p>Available: {res.availableQuantity}</p>
                        <button 
                            disabled={res.availableQuantity <= 0}
                            onClick={() => setSelectedResource(res)}
                            className="book-button"
                        >
                            {res.availableQuantity > 0 ? "Book Now" : "Out of Stock"}
                        </button>
                    </div>
                ))}
            </div>

            {selectedResource && (
                <div className="modal">
                    <div className="modal-content">
                        <button className="close-button" onClick={() => setSelectedResource(null)}>&times;</button>
                        <BookingForm 
                            resource={selectedResource} 
                            onSuccess={onBookingSuccess} 
                            onCancel={() => setSelectedResource(null)}
                        />
                    </div>
                </div>
            )}
        </div>
    );
};

export default Dashboard;
