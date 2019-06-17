import React, {Component} from 'react';
import US108Select from "./US108Select";
import {Button} from 'reactstrap';


class TableBodyUS108 extends Component {
  constructor(props) {
    super(props);
    this.state = {
      check: false,
      name: ''
    };
  };


  handleEdit = () => {
    this.setState(
      prevState => ({
        check: !prevState.check
      })
    );
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
              <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={(event) => {
                this.handleEdit();
                this.state.name = todo.name;
              }}> Edit
              </Button>
            </td>
          </tr>
        ))}
        </tbody>
      );
    } else if (rooms.length > 0 && this.state.check === true) {
      return (<><US108Select name={this.state.name}/></>);
    } else {
      return (<h1>No data ....</h1>);
    }
  }
}

export default TableBodyUS108;
