import React, {Component} from 'react';

class US600NoRedux extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: {},
      isLoaded: false,
    }
  }

  componentDidMount() {
    fetch('http://localhost:9898/houseMonitoring/currentHouseAreaTemperature')
      .then(res => res.json())
      .then((json) => {
        this.setState({
          isLoaded: true,
          item: json,
        })
      })
      .catch(console.log)
  }

  render() {

    var {isLoaded, item} = this.state;

    if (!isLoaded) {
      return <div>Loading...</div>
    } else {
      return (
        <div>
          <h4 key={item.id}>
            Current Temperature: {item} ºC
          </h4>
        </div>
      );
    }
  }
}

export default US600NoRedux;
