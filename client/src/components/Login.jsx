import { Box, Button, TextField, Typography } from '@mui/material';
import React, { useContext, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { LoginContext } from '../App';

const Login = () => {

    const navigate = useNavigate();
    const {setUser} = useContext(LoginContext);


    const[loginId, setLoginId]=useState('');
    const[password, setPassword]=useState('');
    const[message, setMassage] = useState('');

    const handleLoginIdChange = e=>setLoginId(e.target.value);
    const handlePasswordChange = e => setPassword(e.target.value);

    const handleLogin = async()=>{
        try{
            const response = await fetch('/api/login',{
                method:'POST',
                headers:{
                    'Content-Type':'application/json'
                },
                body:JSON.stringify({
                    loginId,
                    password
                })
            });

            if(response.status===401){
                setMassage('ログインIDまたはパスワードが一致しません');
                return;
            }else if(!response.ok){
                setMassage('ログインに失敗しました');
                return;
            }


            const json = await response.json();
            setUser(json);
            console.log(json)
            navigate('/mypage')
            

        

        }catch(e){
            console.log(e);

        }
    }

    
    




  return (
    <div>
        {message&&<Typography>{message}</Typography>}
        <Box display='flex' flexDirection='column'alignItems='start'>
            <Typography variant='h4'>ログイン</Typography>
            <TextField
            label="ログインID"
            value={loginId}
            onChange={handleLoginIdChange}
            />
            <TextField
            label="パスワード"
            value={password}
            type='password'
            onChange={handlePasswordChange}
            />
            <Button onClick={handleLogin}>ログイン</Button>
        </Box>
    </div>
  )
}

export default Login;
