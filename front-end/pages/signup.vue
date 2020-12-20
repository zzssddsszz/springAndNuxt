<template xmlns="">
  <div>
    <div class="page-title-area">
      <div class="container">
        <ul>
          <li>회원가입</li>
        </ul>
      </div>
    </div>

    <section class="signup-area ptb-60">
      <div class="container">
        <div class="signup-content">
          <div class="section-title">
            <h2>회원가입</h2>
          </div>

          <form class="signup-form">
            <div class="form-group">
              <label>아이디</label>
              <input type="text" class="form-control" placeholder="아이디를 입력해 주세요" id="username" name="username">
            </div>

            <div class="form-group">
              <label>이메일</label>
              <input type="email" class="form-control" placeholder="이메일을 입력해주세요" id="emailAddress" name="emailAddress">
            </div>

            <div class="form-group">
              <label>비밀번호</label>
              <input type="password" class="form-control" placeholder="비밀번호를 입력해주세요" id="password" name="password">
            </div>


            <button type="submit" class="btn btn-primary">Signup</button>
          </form>
        </div>
      </div>
    </section>
  </div>
</template>
