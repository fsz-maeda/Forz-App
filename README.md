# FORZ-APP

## 概要
FORZGROUPで使用しているourlyや人事労務、経費精算などのアプリを1つにまとめたWebアプリとなっています。
有給の登録や管理、管理者ページでのアプリ管理なども行えます。

## 主な機能
- ログイン機能
- ログアウト機能
- ユーザー管理機能
- イベント管理機能
- メディア管理機能
- 日報管理機能
- 経費管理機能
- 有給管理機能
- チャット機能

## 技術スタック
- 言語: Java 25
- 画面: JSP
- サーバー処理: Servlet
- Webサーバー: Apache Tomcat 11
- データベース: SQLserver 2.21.1
- ビルド/IDE: Eclipse

## 動作環境
- JDK （バージョン）以上
- Apache Tomcat 11_java25
- SQLserver 2.21.1

## セットアップ手順

### 1. リポジトリのクローン
Eclipse の File > Import > Git からクローンする

### 2. データベースの準備
SQLserver でデータベースとテーブルを作成します。
SQLファイルは `/schema.sql` に用意しています。

### 3. 設定ファイルの編集
`src/main/java/model/Port.java` を作成し、
自分の環境に合わせてDB接続情報を編集します。

JDBC_URL = "jdbc:sqlserver://localhost\\\\\\\\SQLEXPRESS:(自分のポート番号);
			databaseName=master;integratedSecurity=true;encrypt=true;
			trustServerCertificate=true;";

### 4. Eclipseへのインポートと起動
1. Eclipse でプロジェクトをインポート
2. プロジェクトを右クリック > Run As > Run on Server
3. Tomcat を選択して起動

### 5. アクセス
http://localhost:8080/Home

## 使い方
1. 初回はホームの「新規登録」からアカウントを作成
2. 作成したアカウントでログイン
3. メインから各機能を
4. 各機能では基本、登録・編集・削除が可能

## ディレクトリ構成
  FORZ-APP/
    ├── src/main/java/      				… Servlet, DAO などのJavaコード
    ├── src/main/webapp/    				… JSP, CSS, 画像
    ├── src/main/java/model/Port.java  	… データベースの設定ファイル
    └── README.md           				… このファイル

## チームメンバー
- 前田: 管理者機能 / 全体とりまとめ
- 竹内: 日報機能 / 勤怠機能 / 画面設計
- 楠本: イベント機能
- 寺口: メディア機能
- ラーマン: チャット機能 / プロフィール機能