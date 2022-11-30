# Photo Community

## 개요

사진 공유 기능이 있는 소셜 커뮤니티를 만들면서 각종 기술들을 학습하는 프로젝트입니다.

## ⚒️ 사용 기술

- Java 11
- Spring boot 2.7.5
- Jpa 2.1
- MySql 8.0
- Gradle 7.5.1

## ✨ 학습 목표

**페어 프로그래밍**

code with me 를 활용하여 어렵거나 힘든 기능을 의견을 주고 받으며 페어 프로그래밍을 진행합니다.

## 📋 프로젝트 요구사항

### 회원 기능

- [ ]  회원가입
    - [ ]  회원이 이메일, 비밀번호를 입력하여 회원가입을 할 수 있어야 한다.
        - [ ]  이메일, 비밀번호는 필수여야 한다.
            - [ ]  검증 실패시 400 Bad Request 반환
            - [ ]  회원가입 후 200 OK Register process successfully.
        - [ ]  회원가입 전 이메일 인증이 반드시 필요하다.
            - [ ]  이메일로 인증코드를 보내어 제한시간 이내에 입력했을 때 인증이 되어야 한다.
        - [ ]  비밀번호는 암호화되어야 한다. (Bcrypt 해시 암호화 기능 사용)
- [ ]  회원 탈퇴
    - [ ]  회원이 탈퇴할 수 있어야 한다.
    - [ ]  회원 탈퇴는 즉시 이루어지지 않고 30일의 유예 기간을 거친 뒤 정보가 삭제되도록 해야한다. (배치)
- [ ]  회원 정보 수정
    - [ ]  회원이 자신의 정보를 수정할 수 있어야 한다.
        - [ ]  닉네임, 비밀번호 수정이 가능하다.
        - [ ]  profile information can be edited as follows:
            - 닉네임, 이름, 자기소개
- [ ]  회원 조회
    - [ ]  회원이 자신의 정보를 조회할 수 있어야 한다.
        - [ ]  이메일, 닉네임, 프로필, 전화번호 조회
- [ ]  회원 권한
    - [ ]  일반 유저, 관리자, 밴 유저로 구분되어야 한다.
        - [ ]  밴 유저는 게시물을 볼 수 없어야 한다.

프로필 기능

- [ ]  프로필 등록
    - [ ]  회원이 자신의 프로필을 등록할 수 있어야 한다.
        - 닉네임, 이름, 자기소개, 전화번호, 프로필 사진을 정보로 기입할 수 있다.
- [ ]  프로필 수정
    - [ ]  회원이 자신의 프로필을 수정할 수 있어야 한다.
        - 이름, 자기소개, 전화번호, 프로필 사진을 변경할 수 있다.

피드 기능

- [ ]  사진 업로드
    - [ ]  회원은 사진 + 텍스트를 업로드 할 수 있어야 한다.
    - 피드를 작성할때 사진이 반드시 있어야 한다.
    - 각 회원은 다른 회원에게 속한 피드를 작성할 수 없다.
- [ ]  피드에 대해 유저마다 좋아요를 1회 줄 수 있어야 한다.

- [ ]  댓글
    - [ ]  댓글 목록 조회
        - 댓글더보기를 누르면 댓글을 더볼 수 있다. (페이징 없음)
    - [ ]  게시글에 작성자 + 타유저가 댓글을 작성할 수 있다.
    - [ ]  댓글삭제 (본인이 작성한 댓글과 본인이 업로드한 게시물내에 타 유저의 댓글을 삭제할 수 있다.)

최신피드

→ 팔로잉하지 않은 유저들의 피드가 보인다.

### 게시판(커뮤니티) 기능

- [ ]  게시판 조회
- [ ]  게시물 등록
- [ ]  게시물 수정
- [ ]  게시물 삭제
- [ ]  댓글 목록 조회
- [ ]  댓글 등록
- [ ]  댓글 수정
- [ ]  댓글 삭제
- [ ]  페이징 기능

### 팔로우 기능

- [ ]  유저는 상대방을 팔로우 할 수 있다. 상대방을 언팔로우 할 수 있다.
- [ ]  각 유저는 상대방에게 팔로워가 될수도 있고, 팔로잉이 될수도 있다.

### 랭킹 기능 - 피드에 대한 랭킹

- [ ]  가장 많이 좋아요를 받은 피드순으로 랭킹이 조회될 수 있어야 한다.

### 알림 기능

- [ ]  계정 언급 알림
    - [ ]  @가 앞에 붙을 경우 해당 유저에게 알림이 가야 한다.
- [ ]  팔로우 알림
    - [ ]  특정 유저를 팔로우 할 경우 해당 유저에게 알림이 가야한다.

### 채팅 기능

- [ ]  채팅방 기능 구현

### 참고 사항

Git Flow 전략에 따라 버전관리를 합니다. main - develop - feature/{name}
coding convention 은 인텔리제이나 이클립스에서 제공하는 정렬 기능을 사용합니다.
Git commit 메시지를 영어로 작성합니다. 포맷은 [tag#issueNumber] message 이며,
Impl, RC, HotFix, Test, Refactoring, Config 등을 사용할 수 있습니다.
예시) [Impl] User function