document.addEventListener("DOMContentLoaded", function() {

    // 🔽 your whole code goes inside here

    fetch("http://localhost:8080/api/resources")
        .then(res => res.json())
        .then(data => {
            let dropdown = document.getElementById("resId");

            data.forEach(r => {
                let option = document.createElement("option");
                option.value = r.id;
                option.text = r.name + " (Available: " + r.availableQuantity + ")";
                dropdown.appendChild(option);
            });
        });

    document.getElementById("bookingForm").addEventListener("submit", function(e) {

        e.preventDefault();

        const data = {
            empId: document.getElementById("empId").value,
            resId: document.getElementById("resId").value,
            quantity: document.getElementById("quantity").value || 1
        };

        fetch("http://localhost:8080/api/bookings", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        })
        .then(res => res.json())
        .then(result => {
            document.getElementById("result").innerText =
                "✅ Booking Successful! ID: " + result.id;
        });

    });

});


