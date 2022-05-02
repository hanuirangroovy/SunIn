import React, { useState, useEffect } from 'react';



interface Todo{
    text: any;
    speed: any;
    fontSize: any;
    color: any;
}



const TypingText = ({text, speed, fontSize, color}:Todo) => {
    
//   TypingText.defaultProps = {
//     fontSize: '1em', //기본값
//     color: 'black'//기본값
//   }
  const [Text, setText] = useState("");
  const [Count, setCount] = useState(0);

  useEffect(() => {
    let typingText = text ? text : "";//기본값
    let typingSpeed = speed ? speed : 100;//기본값
    const interval = setInterval(() => {
        setText((Text)=>{
          let updated = Text;
          updated = Text + typingText[Count];
          return updated;
        });
        setCount(Count + 1); 
    }, typingSpeed);
    Count === typingText.length && clearInterval(interval);
    return () => clearInterval(interval);
})
  return (
    <p style={{fontSize:`${fontSize}`, color:`${color}`}} >{ Text }</p>
  )
};

export default TypingText;