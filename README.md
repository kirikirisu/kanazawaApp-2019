# kanazawaApp-2019

stockSupporter

# 注意事項

- master で作業しない。
- conflict 起こさないように master 更新されたらローカルの方も更新する。
- 自分が変更したファイル以外は極力上げない。
- ブランチ名はキャメルケースを使う。
- コミット名は日本語で何をしたのかわかりやすくつける。
- Pull Request(PR)と Issue を関連づける。

# 命名規則

## ブランチ名

- 画面を作る場合

```
createScreen-hoge-yourName
```

- やることリストをやった場合

```
toDo-hoge
```

- 何かを修正した場合

```
fix-hoge
```

- 何か機能を追加した場合

```
add-hoge
```

## ID 名

- component の ID

```
android:id="@+id/lowerCamelCase
```

- `app/src/main/res/values` 下の xml ファイルに記述する ID(string.xml の例)

```
<string name="lowerCamelCase">文字列</string>
```

## ファイル名

- `app/src/main/res/drawable` 下のファイル名(png ファイルの例)

```
snake_case.png
```

# 開発手順

- Issue を立てる
- 担当している Issue に Assignees をつける
- Issue を関連付けた Pull Request をたてる
- Pull Request に Assignees と Label をつける
  - 作業中なら「作業中」
  - 作業が完了したなら「レビュー依頼」
- レビューしてもらう
- マージ

## 「ラベル」の使い方

1. レビューを貰う人が PR を出して`「レビュー依頼」`を貼る。
1. 誰か 1 人のレビュワーがレビューした段階で`「レビュー依頼」`を外す。同時に、”見ました”の意味で`「reviewed」`を貼る。
1. レビューを貰った人が、指摘部分を修正してコミット。`「reviewed」`を外し、再び`「レビュー依頼」`を貼る。
1. 2 と 3 を n 回(n∈N)繰り返し、レビュワーが問題無いと判断した場合 Approve する。
1. 任意の人数(例えば 2 人)が Approve したら`「Approved」`を貼る。(最後に Approve した人が貼る。)
1. `「Approved」`が貼られた PR を merge する。
