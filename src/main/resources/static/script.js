function readTextFile(file) {
    const rawFile = new XMLHttpRequest();
    let text;
    rawFile.open("GET", file, false);

    rawFile.onreadystatechange = () => {
        if(rawFile.readyState === 4) {
            if(rawFile.status === 200 || rawFile.status == 0) {
                text = rawFile.responseText;
            }
        }
    }

    rawFile.send(null);

    while (!text) {}
    return text;
}

const loadQuestions = (questions) => {
    const questions_div = document.getElementById("questions");

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

const loadText = () => {
    const data = JSON.parse(readTextFile("data.json"));

    for (const d of data) {
        if (d.id == "questions") {
            loadQuestions(d.questions);
        }
        else {
            document.getElementById(d.id).innerHTML = d.text;
        }
    }
}

const onload = () => {
    loadText();
}

window.onload = () => {
    onload();
}
