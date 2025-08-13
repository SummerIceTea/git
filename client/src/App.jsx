import React, { createContext, useState } from "react";
import { Route, Routes, useNavigate } from "react-router-dom";
import { Box, Button, Grid, Typography } from "@mui/material";
import Login from "./components/Login";
import { MyLibrary } from "./components/MyLibrary";
import { BookSearch } from "./components/BookSearch";
import { MyLibraryDetail } from "./components/MyLibraryDetail";

export const LoginContext = createContext();

const App = () => {
  const navigate = useNavigate();
  const [user, setUser] = useState(null);

  return (
    <LoginContext value={{ user, setUser }}>
      <header>
        <nav>
          <Grid container spacing={1}>
            <Grid>
              {user && (
                <Button variant="text" onClick={() => navigate("/search")}>
                  本検索
                </Button>
              )}
            </Grid>
            <Grid>
              {user && (
                <Button variant="text" onClick={() => navigate("/logout")}>
                  ログアウト
                </Button>
              )}
            </Grid>

            <Grid>
              {user && (
                <Button variant="text" onClick={() => navigate("/mylibrary")}>
                  マイライブラリ
                </Button>
              )}
            </Grid>
          </Grid>
        </nav>

        <Grid>
          {user ? (
            <Typography>{user.name}さん、ようこそ</Typography>
          ) : (
            <Typography>ログインしていません</Typography>
          )}
        </Grid>
      </header>

      <main>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/search" element={<BookSearch />} />
          <Route path="/mylibrary" element={<MyLibrary />} />
          <Route path="/mylibrary/:id" element={<MyLibraryDetail />} />
        </Routes>
      </main>
    </LoginContext>
  );
};
export default App;
