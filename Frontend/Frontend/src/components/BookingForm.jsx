import React, { useState } from "react";
import resourceService from "../services/resourceService";
import authService from "../services/authService";

const BookingForm = ({ resource, onSuccess, onCancel }) => {
    const [quantity, setQuantity] = useState(1);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const handleBooking = async (e) => {
        e.preventDefault();
        setLoading(true);
        setError("");

        const userInfo = authService.getUserInfo();
        if (!userInfo || !userInfo.id) {
            setError("User identification failed. Please log in again.");
            setLoading(false);
            return;
        }

        const bookingData = {
            empId: userInfo.id,
            resId: resource.id,
            quantity: parseInt(quantity)
        };

        try {
            await resourceService.bookResource(bookingData);
            onSuccess();
        } catch (err) {
            setError(err.response?.data?.message || err.response?.data || "Booking failed.");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="booking-form">
            <h3>Book {resource.name}</h3>
            <p>Available Quantity: {resource.availableQuantity}</p>
            {error && <p className="error-message">{error}</p>}
            <form onSubmit={handleBooking}>
                <div className="form-group">
                    <label>Quantity</label>
                    <input
                        type="number"
                        min="1"
                        max={resource.availableQuantity}
                        value={quantity}
                        onChange={(e) => setQuantity(e.target.value)}
                        required
                    />
                </div>
                <div className="button-group">
                    <button type="submit" className="confirm-button" disabled={loading}>
                        {loading ? "Processing..." : "Confirm Booking"}
                    </button>
                    <button type="button" className="cancel-button" onClick={onCancel}>
                        Cancel
                    </button>
                </div>
            </form>
        </div>
    );
};

export default BookingForm;
