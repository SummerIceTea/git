import React, { useState } from "react";
import {
  Button,
  Grid,
  TextField,
  Card,
  CardMedia,
  CardContent,
  Typography,
} from "@mui/material";

export const BookSearch = () => {
  const [keyword, setKeyword] = useState("");
  const [books, setBooks] = useState([]); // 検索結果の本リストを保持

  const handleSearch = async () => {
    if (!keyword.trim()) {
      alert("検索ワードを入力してください");
      return;
    }

    try {
      const response = await fetch(
        `https://www.googleapis.com/books/v1/volumes?q=${encodeURIComponent(
          keyword
        )}`
      );
      const data = await response.json();

      if (data.items) {
        setBooks(data.items);
      } else {
        setBooks([]); // 結果なしは空にする
      }
    } catch (error) {
      console.error("検索エラー:", error);
      setBooks([]);
    }
  };

  const handleRegisterBook = async (info) => {
    try {
      const response = await fetch("/api/mylibrary", {
        method: "POST",
        credentials: "include", // セッション維持
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          title: info.title,
          author: info.authors ? info.authors.join(", ") : "著者不明",
        }),
      });

      if (response.ok) {
        alert("マイライブラリに登録しました！");
      } else {
        alert("登録に失敗しました");
      }
    } catch (err) {
      console.error("登録エラー:", err);
      alert("エラーが発生しました");
    }
  };

  return (
    <div style={{ padding: 20 }}>
      {/* 検索フォーム */}
      <Grid
        container
        spacing={2}
        alignItems="center"
        justifyContent="center"
        sx={{ marginBottom: 4 }}
      >
        <Grid item xs={8} md={6}>
          <TextField
            label="検索"
            fullWidth
            value={keyword}
            onChange={(e) => setKeyword(e.target.value)}
            onKeyDown={(e) => {
              if (e.key === "Enter") handleSearch(); // Enterキーで検索も可能
            }}
          />
        </Grid>
        <Grid item xs={4} md={2}>
          <Button
            variant="outlined"
            fullWidth
            color="primary"
            onClick={handleSearch}
          >
            検索
          </Button>
        </Grid>
      </Grid>

      {/* 検索結果表示 */}
      <Grid container spacing={2}>
        {books.map((book) => {
          const info = book.volumeInfo;
          return (
            <Grid item key={book.id} xs={12} sm={6} md={4} lg={3}>
              <Card sx={{ display: "flex", height: 150 }}>
                {/* 表紙画像 */}
                {info.imageLinks?.thumbnail ? (
                  <CardMedia
                    component="img"
                    sx={{ width: 100 }}
                    image={info.imageLinks.thumbnail}
                    alt={info.title}
                  />
                ) : (
                  <div
                    style={{
                      width: 100,
                      height: "100%",
                      backgroundColor: "#ccc",
                      display: "flex",
                      justifyContent: "center",
                      alignItems: "center",
                      fontSize: 12,
                      color: "#666",
                    }}
                  >
                    No Image
                  </div>
                )}

                {/* タイトルと著者 */}
                <CardContent sx={{ flex: "1 0 auto" }}>
                  <Typography component="div" variant="h6" noWrap>
                    {info.title}
                  </Typography>
                  <Typography
                    variant="subtitle2"
                    color="text.secondary"
                    component="div"
                    noWrap
                  >
                    {info.authors ? info.authors.join(", ") : "著者不明"}
                  </Typography>
                </CardContent>
                <CardContent>
                  <Button
                    variant="outlined"
                    onClick={() => handleRegisterBook(info)}
                  >
                    マイライブラリに登録
                  </Button>
                </CardContent>
              </Card>
            </Grid>
          );
        })}
      </Grid>
    </div>
  );
};
