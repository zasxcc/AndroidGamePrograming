# 게임명 : 카운터 어택

---

## 카피 게임 : devil eater

---

## 게임의 대략적 장르 : 디펜스(팔라독 류)

---

### 게임 진행 루틴

* 플레이어는 왼쪽에서 가만히 대기하고 있다.

* 몬스터는 오른쪽에서 일정 간격을 두고 젠, 가면 갈수록 높은 공격력과 체력의 몬스터가 젠 된다.

* 몬스터는 왼쪽으로 이동하며, 플레이어를 공격할 일정 사거리 내에 도달하면 일정 시간마다 공격한다.

* 플레이어는 몬스터의 공격을 적당한 타이밍에 방어하고, 방어하는 즉시 카운터 공격(오른쪽으로 밀고 나가는 파도 같은 공격)을 가한다. 타이밍에 맞춰 방어하는 것이 실패했을 경우, 감소된 데미지와 함께 그냥 피격 당한다.

* 준비된 일반 몬스터가 다 처리되면 보스 몬스터가 등장한다. 보스 몬스터도 일정 시간마다 공격하지만, 공격 패턴이 다를 것이다.

* 보스 몬스터까지 처리하면 해당 스테이지 클리어, 위에 있을 hp 프로그레스 바가 공격에 의해 0이 되면 게임 오버이다.

* ***교수님 피드백사항 1) 화면이 빌 것 같은데 플레이어가 좌 하단에 고정되고 부채꼴 방향으로 적이 오는 것은 어떤지?***
	* **학생 답변사항**
	* ![deavil eater](https://user-images.githubusercontent.com/43128272/80459263-14b3f500-896d-11ea-8619-6ccaaab689b2.jpg)
	* 카피 게임 이미지입니다. 화면에 배치될 UI가 다수 존재하고, 플레이어나 적의 스킬, 공격 모션 등이 화면에 충분히 보여야 하기 때문에 플레이어를 좌, 적을 우에 배치하도록 기획했습니다.

---

### 사용할 기술적 사항들

* 스프라이트 애니메이션, 몬스터 공격 방어 시 카운터 시스템 등

---

### 배워야 하는 사항들

* 충돌 처리, 몹 스폰 시스템, 프로그레스 바, 씬 전환 등

---

### 가능할지 알기 어려운 부분

* 몬스터의 애니메이션 공격 타이밍을 재 반격 타이밍을 맞추는 기술.

---

### 대략적 클래스 관계도

* GameWorld, GameView, GameObject

	* 대략적으로 위 세 클래스를 기준으로 만들 예정입니다.

---

### 팀원 : 박인혁, 박장호

### 역할 분담

이름|역할 분담|
---|---|
박인혁|애니메이션 구성, 리소스 수집, 게임 로직|
박장호|충돌처리, 게임 오브젝트 관리, 게임 로직|

---

### 개발 일정

주차   |일별|작업내용|진행진척도|
---|---|---|---|
1주차 |6/1, 6/2|플레이어 캐릭터 리소스 수집 및 프레임 워크 제작| 리소스 수집 완료, 프레임 워크는 이해 필요|
1주차 |6/3, 6/4| 캐릭터 애니메이션 및 터치에 따른 이벤트 제작(공격, 스킬, 반격)|공격, 방어 버튼 및 바인딩 완료, 플레이어 캐릭터 리소스 이상함|
1주차 |6/5 | 적 캐릭터 리소스 수집(보스 몬스터1,2 // 일반 몬스터1,2,3)| 적 리소스 수집 완료|
1주차 |6/6, 6/7 | 스테이지 배경 리소스 수집 및 스테이지 제작, 1주차 미비점 보완| 배경 리소스 수집 완료|

---

주차   |일별|작업내용|진행진척도|
---|---|---|---|
2주차 |6/8, 6/9 | 일반 몬스터 애니메이션 및 AI 제작| 몬스터 이동, 공격 구현 완료|
2주차 |6/10 | 보스 몬스터1 애니메이션 및 AI 제작| 보스 몬스터1 이동, 공격 구현 완료|
2주차 |6/11 | 보스 몬스터2 애니메이션 및 AI 제작| 보스 몬스터 이도으 공격 구현 완료|
2주차 |6/12 | 충돌처리 및 반격 시스템 제작| 반격 보류, 공격시 검기 나가도록 구현, 적과 플레이어, 검기와 적 충돌처리 완료|
2주차 |6/13, 6/14 | 스테이지 제작 및 2주차 미비점 보완| 스테이지 제작X, 프레임 애니메이션이 끝나면 Notify하도록 만들려고 하는데 잘안댐|

---

주차   |일별|작업내용|진행진척도|
---|---|---|---|
3주차 |6/15|메인 화면 및 UI 제작 -> 변경 : 일반 몬스터 오브젝트 풀 제작| 저번주의 밀린 작업들로 인해 계속 작업이 밀리는 중|
3주차 |6/16|일반 몬스터 오브젝트 풀 및 스폰 시스템 제작 -> 변경 : 반격 시스템 제작| 반격 시스템 제작 완료, 적 스폰 시스템 완료 |
3주차 |6/17|몬스터 사망시 스코어 적용, 배경 적용|스코어 적용 완료, 배경 적용 완료, 몬스터 사망 애니메이션 재생 적용| 
3주차 |6/18|Title, Game, BestScore 등 씬 처리 -> 추가 : UI (플레이어 체력바) 제작 | 플레이어 UI 제작, 몬스터 공격 타이밍과 플레이어 방어 타이밍 조정, 스코어에 따른 적 스폰 조절|
3주차 |6/19|미비점 보완| java파일 정리, 적 모션 수정, 이펙트 추가|
3주차 |6/20, 6/21| 미비점 보완, 게임성↑| 스코어 신 추가, 보스1,2 제작 및 추가 완료, 퍼펙트 가드시 검기 나가도록 수정, 사운드 추가|
4주차 |6/22|발표|

---

### 진행 및 애로 사항

* 캐릭터, 몬스터 및 보스 리소스 수집 -> 스프라이트 기준점을 못맞춰서 재생이 이상하게 댐 -> 반해결
* 스프라이트 행동 별로 잘라서 편집 -> 완료
* 캐릭터 idle 상태와 attack 상태 둘 다 프레임 6개로 나뉘는데 attack의 가로 길이가 더 길어서 캐릭터가 공격할 때 가로로 쪼그라듦. 어떻게 해결해야 하는지..? -> 해결
* 프레임 애니메이션이 끝나면 Notify하도록 하려하는데 이상한데서 오류가 발생, 그에 따른 작업이 다 밀려버림 -> 6/15 해결
* 하이스코어 나오도록 하는거 결국 못함..
