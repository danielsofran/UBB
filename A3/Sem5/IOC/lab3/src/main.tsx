import React from 'react'
import ReactDOM from 'react-dom'
import App from './App'
import axe from 'react-axe'


axe(React, ReactDOM, 1000)
  .then(() => {
    console.log('ready')
  })
  .catch((err) => {
    console.error(err)
  })


ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
)
