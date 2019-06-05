import React, {Component} from 'react';


class US147GetResult extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
      sensors: false,
      gridID: '',
      roomID: ''
    }
  }

  componentDidMount() {
    fetch('http://localhost:9898/gridSettings/grids/' + this.props.gridID)
      .then(res => res.json())
      .then((json) => {
        this.setState({
          sensors: true,
          item: json,
        })
      })
      .catch(console.log)
  }

  render() {
    var {id, item} = this.state;
    return (
      <div>


        <ul>
          {item.map(item => (
            <li key={item.name}>
              Name: {item.name} | Floor: {item.floor} | Height: {item.height} | Length: {item.length} |
              Width: {item.width}
            </li>
          ))}
        </ul>

      </div>
    );
  }

}

export default US147GetResult;
