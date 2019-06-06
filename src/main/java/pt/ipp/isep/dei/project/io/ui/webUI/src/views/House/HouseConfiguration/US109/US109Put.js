import React, {Component} from 'react';


class US109Put extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
    }
  }


  componentDidMount() {
    const name = this.props.name;
    const floor = this.props.floor;
    const width = this.props.width;
    const length = this.props.length;
    const height = this.props.height;
    console.log(this.props);
    fetch('http://localhost:9898/houseSettings/room', {
      method: 'put',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({name, floor, width, length, height})
    })
      .then(res => res.json())
      .then((json) => {
        this.setState({
          item: json,
        })
      })
      .catch(console.log);
  };


  render() {
    return (
      <div>
        <p>The room has been altered to the following configuration:</p>
          <ul>
            <li>
            Name: {this.props.name}
            </li>
            <li>
            Floor: {this.props.floor}
            </li>
            <li>
            Width: {this.props.width}
            </li>
            <li>
            Length: {this.props.length}
            </li>
            <li>
            Height: {this.props.height}
            </li>
          </ul>
      </div>
    );


  }
}

export default US109Put;
