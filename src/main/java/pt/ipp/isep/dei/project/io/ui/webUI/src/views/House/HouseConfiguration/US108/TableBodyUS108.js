import React, {Component} from 'react';
import US108Collapse from "./US108Collapse";


class TableBodyUS108 extends Component {
  constructor(props) {
    super(props);
  };


  render() {
    const {data} = this.props; // data = this.props.data;
    if (data.length > 0) {
      return (
        <tbody>
        {data.map((todo) => (
          <tr key={todo.name}>
            <td>{todo.name}</td>
            <td>{todo.floor}</td>
            <td>{todo.height}</td>
            <td>{todo.length}</td>
            <td>{todo.width}</td>
            <td><US108Collapse/></td>
          </tr>
        ))}
        </tbody>
      );
    } else {
      return (<h1>No data ....</h1>);
    }
  }
}

export default TableBodyUS108;
