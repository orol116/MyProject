
// 전체선택
function selectAll(selectAll){
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach((checkbox)=>{checkbox.checked=selectAll.checked})
};

(function(){
    const deleteBtn = document.getElementById("deleteBtn"); 

    //member/delete?type=1&cBoard=107
    if(deleteBtn != null){// 버튼이 화면에 존재할때
        deleteBtn.addEventListener("click", function(){


            

            return true;
        });
    }
})();


// java.lang.NullPointerException:
// Cannot read the array length because "elements" is null

// 체크박스 선택 안됐을때, 
function ckBox() {
    if(!confirm("정말로 삭제 하시겠습니까?")){
        return false;
    }


    const deleteBtn = document.getElementById("deleteBtn");
    let cBoard = document.querySelectorAll("[name='cBoard']:checked");
    let cReply = document.querySelectorAll("[name='cReply']:checked");

    //document.querySelectorAll("[name='cReply']:checked").length

    let deleteNo = [];

    // 작성글
    if(document.querySelectorAll("[name='cBoard']").length != 0 ){
        if(cBoard.length == 0){
            alert("삭제할 글을 선택해주세요.");
            return false;
        }

        for(let b of cBoard){
            deleteNo.push( b.value );
        }

    }

    // 작성 댓글
    if(document.querySelectorAll("[name='cReply']").length != 0 ){ 
        if(cReply.length == 0){
            alert("삭제할 댓글을 선택해주세요.");
            return false;
        }

        for(let r of cReply){
            deleteNo.push( r.value );
        }
    }

    document.getElementById("deleteNo").value = deleteNo.join(","); // 1,2,3
    
    return true;
  
}