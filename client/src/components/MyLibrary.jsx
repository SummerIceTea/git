import React, { useEffect, useState } from "react";
import { Card, CardContent, Typography, Grid } from "@mui/material";
import { useNavigate } from "react-router-dom";

export const MyLibrary = () => {
  const [books, setBooks] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetch("/api/mylibrary")
      .then((res) => res.json())
      .then((data) => setBooks(data))
      .catch(() => setBooks([]));
  }, []);

  return (
    <div style={{ padding: 20 }}>
      <Typography variant="h4" gutterBottom>
        マイライブラリ
      </Typography>

      <Grid container spacing={2}>
        {books.length === 0 && (
          <Typography variant="body1">登録された本はありません</Typography>
        )}
        {books.map((book) => (
          <Grid item xs={12} sm={6} md={4} lg={3} key={book.id}>
            <Card
              sx={{ cursor: "pointer" }}
              onClick={() => navigate(`/mylibrary/${book.id}`)}
            >
              <CardContent>
                <Typography variant="h6" noWrap>
                  {book.title}
                </Typography>
                <Typography variant="subtitle2" color="text.secondary" noWrap>
                  {book.author}
                </Typography>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
    </div>
  );
};
