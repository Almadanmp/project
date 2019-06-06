import React, {Component} from 'react';
import {Table} from "reactstrap";

class TableBody extends Component {
  constructor(props) {
    super(props);
  }


  render() {
    const {data} = this.props; // data = this.props.data;
    if (data.length > 0) {
      return (
        <tbody>
        {data.map((todo) => (
          <tr key={todo.name}>
            <td>{todo.name}</td>
            <td>{todo.floor}</td>
            <td>{todo.height}m</td>
            <td>{todo.length}m</td>
            <td>{todo.width}m</td>
          </tr>
        ))}
        </tbody>

      );
    } else {
      return (<h1>No data ....</h1>);
    }
  }

}

export default TableBody;
