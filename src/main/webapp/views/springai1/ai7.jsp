<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script>
  let ai7 = {
    init:function(){
      $('#send').click(()=>{
        this.send();
      });
      $('#spinner').css('visibility','hidden');
    },
    send: async function(){
      $('#spinner').css('visibility','visible');
      let language = $('#language').val();

      let question = $('#question').val();
      let qForm = `
            <div class="media border p-3">
              <img src="/image/user.png" alt="John Doe" class="mr-3 mt-3 rounded-circle" style="width:60px;">
              <div class="media-body">
                <h6>John Doe</h6>
                <p>`+question+`</p>
              </div>
            </div>
    `;
      $('#result').prepend(qForm);

      const response = await fetch('/ai1/self-consistency', {
        method: "post",
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Accept': 'application/x-ndjson' //라인으로 구분된 청크 텍스트
        },
        body: new URLSearchParams({ content: question })
      });

      let uuid = this.makeUi("result");

      const reader = response.body.getReader();
      const decoder = new TextDecoder("utf-8");
      let content = "";
      while (true) {
        const {value, done} = await reader.read();
        if (done) break;
        let chunk = decoder.decode(value);
        content += chunk;
        console.log(content);
        $('#'+uuid).html(content)
      }
      $('#spinner').css('visibility','hidden');

    },
    makeUi:function(target){
      let uuid = "id-" + crypto.randomUUID();

      let aForm = `
          <div class="media border p-3">
            <div class="media-body">
              <h6>GPT4 </h6>
              <p><pre id="`+uuid+`"></pre></p>
            </div>
            <img src="/image/assistant.png" alt="John Doe" class="ml-3 mt-3 rounded-circle" style="width:60px;">
          </div>
    `;
      $('#'+target).prepend(aForm);
      return uuid;
    }

  }

  $(()=>{
    ai7.init();
  });

</script>


<div class="col-sm-10">
  <h2>Spring AI 7</h2>
  <div class="row">
    <div class="col-sm-8">
      <textarea id="question" class="form-control">안녕하세요, 저는 당신이  개발한 웹 관리 시스템을 사용해보았습니다. 정말 훌륭한 오픈 소스 콘텐츠 관리 시스템이죠. 많은 멋진 사용자 플러그인들이 제공되고, 설정하기도 꽤 쉽습니다. 하지만 연락처 양식에서 버그를 발견했어요. 이름 필드를 선택할 때 발생하는 문제입니다. 제가 이름 필드에 텍스트를 입력하는 화면을 첨부한 스크린샷을 참고해주세요. 제가 호출한 JavaScript 알림 상자를 확인할 수 있습니다. 그렇지만 나머지는 정말 좋은 웹사이트입니다. 저는 그것을 사용하는 것을 즐깁니다그 버그는 웹사이트에 남겨두셔도 괜찮아요, 그게 저에게 더 재미있는 읽을 거리를 제공하니까요. 감사합니다.</textarea>
    </div>
    <div class="col-sm-2">
      <button type="button" class="btn btn-primary" id="send">Send</button>
    </div>
    <div class="col-sm-2">
      <button class="btn btn-primary" disabled >
        <span class="spinner-border spinner-border-sm" id="spinner"></span>
        Loading..
      </button>
    </div>
  </div>


  <div id="result" class="container p-3 my-3 border" style="overflow: auto;width:auto;height: 300px;">

  </div>

</div>
