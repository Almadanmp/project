import React, {Component} from 'react';

class TableBody extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    const {rooms} = this.props; // data = this.props.data;
    if (rooms.length > 0) {
      return (
        <tbody>
        {rooms.map((todo) => (
          <tr key={todo.name}>
            <td>{todo.name}</td>
            <td>{todo.floor}</td>
            <td>{todo.height}</td>
            <td>{todo.length}</td>
            <td>{todo.width}</td>
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
