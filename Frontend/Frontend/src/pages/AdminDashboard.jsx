import React, { useState, useEffect } from "react";
import resourceService from "../services/resourceService";
import authService from "../services/authService";
import { useNavigate } from "react-router-dom";
import "./Register.css";

const AdminDashboard = () => {
    const [resources, setResources] = useState([]);
    const [name, setName] = useState("");
    const [quantity, setQuantity] = useState(0);
    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");
    const navigate = useNavigate();

    const fetchResources = async () => {
        try {
            const data = await resourceService.getAllResources();
            setResources(data);
        } catch (err) {
            console.error(err);
        }
    };

    useEffect(() => {
        fetchResources();
    }, []);

    const handleLogout = () => {
        authService.logout();
        navigate("/login");
    };

    const handleAddResource = async (e) => {
        e.preventDefault();
        try {
            await resourceService.addResource({ name, availableQuantity: parseInt(quantity) });
            setSuccess("Resource added successfully!");
            setError("");
            setName("");
            setQuantity(0);
            fetchResources();
        } catch (err) {
            setError("Failed to add resource. Ensure you have Admin privileges.");
            setSuccess("");
        }
    };

    const handleDeleteResource = async (id) => {
        try {
            await resourceService.deleteResource(id);
            fetchResources();
        } catch (err) {
            console.error("Failed to delete resource", err);
        }
    };

    return (
        <div className="dashboard-container">
            <header className="dashboard-header">
                <h1>Admin Dashboard (Resource Management)</h1>
                <div>
                   <button onClick={() => navigate("/dashboard")} className="book-button" style={{marginRight: '10px'}}>User Dashboard</button>
                   <button onClick={handleLogout} className="logout-button">Logout</button>
                </div>
            </header>

            <div className="register-card" style={{margin: '20px auto'}}>
                <h2>Add New Resource</h2>
                {error && <p className="error-message">{error}</p>}
                {success && <p className="success-message" style={{color: 'green'}}>{success}</p>}
                <form onSubmit={handleAddResource}>
                    <div className="form-group">
                        <label>Resource Name</label>
                        <input
                            type="text"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label>Quantity</label>
                        <input
                            type="number"
                            min="1"
                            value={quantity}
                            onChange={(e) => setQuantity(e.target.value)}
                            required
                        />
                    </div>
                    <button type="submit" className="register-button">Add Resource</button>
                </form>
            </div>

            <div className="resource-list">
                {resources.map((res) => (
                    <div key={res.id} className="resource-card">
                        <h3>{res.name}</h3>
                        <p>Available: {res.availableQuantity}</p>
                        <button onClick={() => handleDeleteResource(res.id)} className="logout-button">Delete</button>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default AdminDashboard;
