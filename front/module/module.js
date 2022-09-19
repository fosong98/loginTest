export function display(str) {
    let h1 = document.createElement("h1");
    h1.innerText = str;
    document.body.append(h1);
    alert(JSON.parse(str)._embedded.employeeList[0].name);
}