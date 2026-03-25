
//for login page 

document.getElementById("empForm").addEventListener("submit",function(e){
    e.preventDefault();

    const formData={
        fname:e.target.fname.value,
        lname:e.target.lname.value,
        role:e.target.role.value
    };

    //send to backend 
    fetch("http://localhost:8080/api/employees",{
     method:"POST",
     headers:{
        "Content-Type":"application/json"

     }  ,
     body:JSON.stringify(formData) 
    })
    .then(res => res.json())
    .then(data=>{
        alert("Employe added succesfully");
        console.log(data);
    })
    .catch(err=>{
        console.log(err);
        alert("Error adding Employee");

    });
});