b1.onclick = () => {
    let url = "/bank_thor_v2/bank";
    fetch(url)
        .then(response => response.json())
        .then(res => {
            let data = "<table class = 'table table-striped table-bordered'> <thead class='thead-dark'> <tr><th>UserId</th><th>First Name</th><th>Last Name</th><th>EmailId</th><th>Mobile Number</th></thead><tbody> "
            res.forEach(e => {
                data = data + "<tr><td>" + e.userId + "</td>";
                data = data + "<td>" + e.firstName + "</td>";
                data = data + "<td>" + e.lastName + "</td>";
                data = data + "<td>" + e.emailId + "</td>";
                data = data + "<td>" + e.mobileNumber + "</td></tr>";
            });
            data = data + "</tbody></table>"
            document.getElementById("results").innerHTML = data;
        })
}
b2.onclick = () => {
    let url = "/bank_thor_v2/transaction";
    fetch(url)
        .then(response => response.json())
        .then(res => {
            let data = "<table class = 'table table-striped table-bordered'> <thead class='thead-dark'> <tr><th>Account Number</th><th>Name</th><th>Account Balance</th><th>PanCard</th><th>UserId</th></thead><tbody> "
            res.forEach(e => {
                data = data + "<tr><td>" + e.accountNumber + "</td>";
                data = data + "<td>" + e.name + "</td>";
                data = data + "<td>" + e.openingBalance + "</td>";
                data = data + "<td>" + e.panCard + "</td>";
                data = data + "<td>" + e.userId1 + "</td></tr>";
            });
            data = data + "</tbody></table>"
            document.getElementById("results").innerHTML = data;
        })
}

b3.onclick = () => {
    let url = "/bank_thor_v2/all";
    fetch(url)
        .then(response => response.json())
        .then(res => {
            let data = "<table class = 'table table-striped table-bordered'> <thead class='thead-dark'> <tr><th>Transaction Id</th><th>TransactionType</th><th>Opening Balance</th><th>Transaction Amount</th><th>Closing Balance</th><th>Account Number</th><th>Date</th></thead><tbody> "
            res.forEach(e => {
                data = data + "<tr><td>" + e.transId + "</td>";
                data = data + "<td>" + e.transType + "</td>";
                data = data + "<td>" + e.openingBalance1 + "</td>";
                data = data + "<td>" + e.amount + "</td>";
                data = data + "<td>" + e.closingBalance + "</td>";
                data = data + "<td>" + e.accountNumber + "</td>";
                data = data + "<td>" + e.date + "</td></tr>";
            });
            data = data + "</tbody></table>"
            document.getElementById("results").innerHTML = data;
        })
}
document.getElementById("b4").onclick = function () {
    location.href = "http://localhost:8080/bank_thor_v2/employee.html";
};
document.getElementById("b5").onclick = function () {
    location.href = "http://localhost:8080/bank_thor_v2/delete.html";
};
// b5.onclick = () => {
//     let value = document.getElementById("accountnumber").value;
//     let url = "/bank_thor_v2/delete?accoutNumber = " + values;
//     fetch(url)
//         .then(response => response.json())
//         .then(res => {
//             ley data = "<p> data <p>";
//         });



// }
