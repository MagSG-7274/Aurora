function readTextFile(file) {
    const rawFile = new XMLHttpRequest();
    let text;
    rawFile.open("GET", file, false);

    rawFile.onreadystatechange = () => {
        if(rawFile.readyState === 4) {
            if(rawFile.status === 200 || rawFile.status == 0) {
                text = JSON.parse(rawFile.responseText);
            }
        }
    }

    rawFile.send(null);

    while (!text) {}
    return text;
}

const onload = () => {
    const questions_div = document.getElementById("questions");
    const questions = readTextFile("questions.json");

    for (const question of questions) {
        const q = document.createElement("p");
        const a = document.createElement("p");
        q.classList.add("question-title");
        a.classList.add("question-answer");
        q.innerHTML = question.question;
        a.innerHTML = question.answer;

        const d = document.createElement("div");
        d.appendChild(q);
        d.appendChild(a);

        questions_div.appendChild(d);
    }
}

window.onload = () => {
    onload();
}
