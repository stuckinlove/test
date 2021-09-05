# monsterlab-test-movie.search.api

### Tech Stack
* java 11<br>
* spring-Boot 2.5.4<br>
* h2

### API endpoints
* GET: /favorites - return that the user has previously marked as favorite.<br>
* POST: /favorite/:id - add a favorite movie<br>
* GET: /movies?search={search} - return popular movies or what the user searched for<br>
* GET: /movies/:id - return that specific movie in detail<br>
※/movies - This should take apiKey

### テスト実施方法
・API-KEYテスト(postmanなど)<br>
 ex) curl -v --header "API_KEY:TEST-SECRET-KEY" http://localhost:8080/movies?search

・単位テスト(Junitで実施)<br>
 以下、テストクラス在り処<br>
 test/java/com/monlb/movie/controller<br>
  　∟SearchControllerTest.java・・・Junitテストクラス<br>

### テーブル
customer : customer_favorite = 1 : N<br>
movie_header : customer_favorite = 1 : N<br>
movie_header : movie_detail = 1 : 1<br>

<h2>構成ファイル</h2>
data.sql・・・動作確認用の初期データ<br>
application.properties・・・Spring、H2設定およびAPIキー定義<br>

AuthConfig.java・・・API KEY認証関連<br>
AuthFilter.java・・・API KEY認証関連<br>

SearchController.java・・・コントローラークラス<br>

SearchService.java・・・ビジネスロジックのインターフェース<br>
SearchServiceImpl.java・・・インターフェースの実装クラス<br>

CustomerFavoriteRepository.java・・・repositoryクラス<br>
CustomerRepository.java・・・repositoryクラス<br>
MovieDetailRepository.java・・・repositoryクラス<br>
MovieHeaderRepository.java・・・repositoryクラス<br>

Customer.java・・・エンティティクラス<br>
CustomerFavorite.java・・・エンティティクラス<br>
MovieDetail.java・・・エンティティクラス<br>
MovieHeader.java・・・エンティティクラス<br>
