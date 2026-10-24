var ageInput = document.getElementById("ageInput");

ageInput.addEventListener("focus", function() {
    if (this.value === "0") {
        this.value = "";
    }
});