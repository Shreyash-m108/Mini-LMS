import React from "react";

function Body() {
  const hurray = () => toast.info("WoW");
  return (
    <div className="welcome">
      <h1>Welcome, User</h1>
    </div>
  );
}

export default Body;
