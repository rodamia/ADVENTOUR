/*mpg_index.js*/

$(document).ready(function() {
//	헤더랑 푸터 담은 mpg_nvbox.html 로드
    $("#mpg_nvbox").load("mpg_nvbox.html");
});


//게시판과 페이징 관련

//	문의하기 페이지로 이동
function movePost() {
	window.location.href='/adventour/inquiry_add_post.html';
}

let currentPage = 1;  // 현재 페이지
const itemsPerPage = 10;  // 페이지 당 게시글 수
const totalPages = Math.ceil(posts.length / itemsPerPage);  // 전체 페이지 수

function renderPagination() {
    const paginationElement = document.getElementById('divPagination');
    let paginationContent = '';

    for(let i = 1; i <= totalPages; i++) {
        if (i === currentPage) {
            paginationContent += `<a href="#" class="active">${i}</a>`;
        } else {
            paginationContent += `<a href="#" onclick="switchPage(${i})">${i}</a>`;
        }
    }

    paginationElement.innerHTML = `
        <a href="#" onclick="switchPage(1)" ${currentPage === 1 ? 'disabled' : ''}><<</a>
        <a href="#" onclick="switchPage(${currentPage - 1})" ${currentPage === 1 ? 'disabled' : ''}><</a>
        ${paginationContent}
        <a href="#" onclick="switchPage(${currentPage + 1})" ${currentPage === totalPages ? 'disabled' : ''}>></a>
        <a href="#" onclick="switchPage(${totalPages})" ${currentPage === totalPages ? 'disabled' : ''}>>></a>
    `;
}

function switchPage(pageNumber) {
    if (pageNumber < 1 || pageNumber > totalPages) {
        return;
    }
    currentPage = pageNumber;
    renderPosts(document.getElementById('postDivList'));
    renderPagination();
}

renderPagination();



//게시판과 페이징 관련



