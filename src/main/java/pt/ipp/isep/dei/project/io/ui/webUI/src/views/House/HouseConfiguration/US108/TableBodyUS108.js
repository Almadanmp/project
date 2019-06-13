import React, {Component} from 'react';
import US108Select from "./US108Select";


class TableBodyUS108 extends Component {
  constructor(props) {
    super(props);
    this.state = {
      check: false
    };
  };


  render() {
    const {rooms} = this.props; // data = this.props.data;
    if (rooms.length > 0 && this.state.check === false) {
      return (
        <tbody>
        {rooms.map((todo) => (
          <tr key={todo.name}>
            <td>{todo.name}</td>
            <td>{todo.floor}</td>
            <td>{todo.height}</td>
            <td>{todo.length}</td>
            <td>{todo.width}</td>
            <td>
              <button onClick={(e) => this.setState(prevState => ({
                check: !prevState.check
              }))}> Edit
              </button>
            </td>
          </tr>
        ))}
        </tbody>
      );
    } else if (rooms.length > 0 && this.state.check === true) {
      return (<><US108Select/></>);
    } else {
      return (<h1>No data ....</h1>);
    }
  }
}

export default TableBodyUS108;
