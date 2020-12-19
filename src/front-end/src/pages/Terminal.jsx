import * as React from "react";

export const Terminal = () => {
  return <Textbox />;
};

function Textbox() {
  return (
    <table className="terminal">
      <tr>
        <td>y</td>
        <td>y</td>
        <td>y</td>
      </tr>
      <tr>
        <td>y</td>
        <td>
          <textarea></textarea>
        </td>
        <td>y</td>
      </tr>
      <tr>
        <td>y</td>
        <td>y</td>
        <td>y</td>
      </tr>
    </table>
  );
}
