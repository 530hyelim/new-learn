document.querySelectorAll('.slider[data-simple-slider]').forEach(el => {
    simpleslider.getSlider(el);
});

function loadContent(type, cbFunction) {
    fetch(contextPath + '/mypage/' + type + '?mypageNo=' + mypageNo)
        .then(response => {
            if (!response.ok) throw new Error('에러 발생');
            return response.text();
        })
        .then(html => {
            document.querySelector(".container").innerHTML = html;
            if (typeof cbFunction === 'function') cbFunction();
        })
        .catch(error => {
            document.querySelector(".container").innerHTML = '<p>콘텐츠를 불러오지 못했습니다.</p>';
        });
}

function onDateClick(date) {
    fetch(contextPath + '/mypage/calendar?mypageNo=' + mypageNo + '&date=' + date)
        .then(response => {
            if (!response.ok) throw new Error('에러 발생');
            return response.text();
        })
        .then(html => {
            document.querySelector(".container").innerHTML = html;
        })
        .catch(error => {
            document.querySelector(".container").innerHTML = '<p>콘텐츠를 불러오지 못했습니다.</p>';
        });
}

//$('#edit-btn').click(function() { 수정 버튼이 페이지 로딩 이후 load calendar 함수에서 fetch로 불러와서 
//동적으로 삽입되고 있기 때문에 이벤트가 등록되지 않음 => 상위 요소에 이벤트 위임
$(document).on('click', '.modal-btn', function() {
    const btn = $(this);
    const dmlType = btn.data('dmltype');
    const eventNo = btn.data('eventno');
    
    fetch(contextPath + '/mypage/modal/'+dmlType+'?eventNo='+eventNo)
    .then(response => {
        if (!response.ok) throw new Error('에러 발생');
        return response.text();
    })
    .then(html => {
        document.querySelector(".modal-position").innerHTML = html;
        new bootstrap.Modal(document.getElementById('exampleModal')).show();
    })
    .catch(error => {
        document.querySelector(".modal-position").innerHTML = '<p>콘텐츠를 불러오지 못했습니다.</p>';
    });
});

$(document).on('submit', '#searchForm', function(e) {
    e.preventDefault(); // 기본 동작 방지
    
    fetch(contextPath + '/mypage/storage/search')
    .then(response => {
        if (!response.ok) throw new Error('에러 발생');
        return response.text();
    })
    .then(html => {
        document.querySelector(".storage-main-body").innerHTML = html;
    })
    .catch(error => {
        document.querySelector(".storage-main-body").innerHTML = '<p>콘텐츠를 불러오지 못했습니다.</p>';
    });
});

$(document).on('click', '.repo-btn', function() {
    const btn = $(this);
    const parentRepoNo = $(this).data('repo-no');
    const currentLevel = parseInt($(this).data('repo-level'));
    const nextLevel = currentLevel + 1;
    const target = $('.repo-item.lvl-'+nextLevel+'.prn-'+parentRepoNo);
    
    $('.repo-item').each(function() {
        const itemLevel = parseInt($(this).find('.repo-btn').data('repo-level'));
        if (itemLevel > currentLevel) {
            $(this).slideUp(200);				
        }
    });
    if (target.css("display") === "none") {
        target.slideDown(200);
    }
    
    fetch(contextPath + '/mypage/storage/load?repoNo='+parentRepoNo)
    .then(response => {
        if (!response.ok) throw new Error('에러 발생');
        return response.text();
    })
    .then(html => {
        document.querySelector(".storage-main-body").innerHTML = html;
    })
    .catch(error => {
        document.querySelector(".storage-main-body").innerHTML = '<p>콘텐츠를 불러오지 못했습니다.</p>';
    });
});

$(document).on('click', '#all-files-btn', function() {
    fetch(contextPath + '/mypage/storage/search')
    .then(response => {
        if (!response.ok) throw new Error('에러 발생');
        return response.text();
    })
    .then(html => {
        document.querySelector(".storage-main-body").innerHTML = html;
    })
    .catch(error => {
        document.querySelector(".storage-main-body").innerHTML = '<p>콘텐츠를 불러오지 못했습니다.</p>';
    });
});