//モーダル表示
function modalOpen() {
    document.querySelector('.modalWrapper').style.display = 'block';
    document.querySelector('.modalArea').style.display = 'block';
}

//モーダル非表示
function modalClose() {
    document.querySelector('.modalArea').style.display = 'none';
}

//ボタンクリックリスナー
document.querySelector('#openModal').addEventListener('click', modalOpen);
document.querySelector('.closeModal').addEventListener('click', modalClose);

