import React, { useEffect, useState } from "react";
import { TextField, Button, Typography, Container, Box } from "@mui/material";
import { useParams, useNavigate } from "react-router-dom";

export const MyLibraryDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [book, setBook] = useState(null);
  const [shopName, setShopName] = useState("");
  const [buyDate, setBuyDate] = useState("");
  const [comments, setComments] = useState("");

  useEffect(() => {
    fetch(`/api/mylibrary/${id}`)
      .then((res) => {
        if (!res.ok) throw new Error("データ取得失敗");
        return res.json();
      })
      .then((data) => {
        setBook(data);
        setShopName(data.shopName || "");
        setBuyDate(data.buyDate || "");
        setComments(data.comments || "");
      })
      .catch(() => alert("本の情報を取得できませんでした"));
  }, [id]);

  const handleSave = () => {
    const updated = {
      shopName,
      buyDate,
      comments,
    };

    fetch(`/api/mylibrary/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(updated),
    })
      .then((res) => {
        if (!res.ok) throw new Error("保存失敗");
        alert("保存しました");
        navigate("/mylibrary"); // 詳細から一覧へ戻る
      })
      .catch(() => alert("保存に失敗しました"));
  };

  if (!book) return <div>読み込み中...</div>;

  return (
    <Container maxWidth="sm" sx={{ mt: 4 }}>
      <Typography variant="h4" gutterBottom>
        本の詳細
      </Typography>

      <Box mb={2}>
        <TextField
          label="タイトル"
          fullWidth
          value={book.title}
          InputProps={{ readOnly: true }}
          variant="outlined"
          margin="normal"
        />
        <TextField
          label="著者"
          fullWidth
          value={book.author}
          InputProps={{ readOnly: true }}
          variant="outlined"
          margin="normal"
        />
        <TextField
          label="購入店"
          fullWidth
          value={shopName}
          onChange={(e) => setShopName(e.target.value)}
          variant="outlined"
          margin="normal"
        />
        <TextField
          label="購入日"
          type="date"
          fullWidth
          value={buyDate}
          onChange={(e) => setBuyDate(e.target.value)}
          variant="outlined"
          margin="normal"
          InputLabelProps={{ shrink: true }}
        />
        <TextField
          label="コメント"
          fullWidth
          multiline
          rows={4}
          value={comments}
          onChange={(e) => setComments(e.target.value)}
          variant="outlined"
          margin="normal"
        />
      </Box>

      <Button variant="contained" color="primary" onClick={handleSave}>
        保存
      </Button>
    </Container>
  );
};
